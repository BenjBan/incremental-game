import { createContext, useContext, useState, useEffect, useCallback, ReactNode } from 'react'

type Upgrade = {
    id: number;
    name: string;
    description: string;
    level: number;
    income: number;
    incomePerSecond: number;
    speed: number;
    cost: number;
    costDisplay: string;
}

type GameContextType = {
    slotId: number | null;
    slotName: string;
    money: number;
    incomePerSecond: number;
    upgrades: Upgrade[];
    loading: boolean;
    buyUpgrade: (upgradeId: number) => Promise<boolean>;
    setSlotId: (id: number) => void;
}

const GameContext = createContext<GameContextType | undefined>(undefined)

export function GameProvider({ children }: { children: ReactNode }) {
    const [slotId, setSlotId] = useState<number | null>(null)
    const [slotName, setSlotName] = useState('')
    const [money, setMoney] = useState(0)
    const [incomePerSecond, setIncomePerSecond] = useState(0)
    const [upgrades, setUpgrades] = useState<Upgrade[]>([])
    const [loading, setLoading] = useState(true)

    // Fetch game state when slotId changes
    useEffect(() => {
        if (slotId === null) {
            setLoading(true)
            return
        }
        fetchGameState()
    }, [slotId])

    // Passive income tick
    useEffect(() => {
        if (slotId === null || incomePerSecond === 0) return

        const interval = setInterval(async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/game/${slotId}/tick`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ deltaSeconds: 1 })
                })
                const data = await response.json()
                if (data.success) {
                    setMoney(data.money)
                    setIncomePerSecond(data.incomePerSecond)
                }
            } catch (error) {
                console.error('Error processing tick:', error)
            }
        }, 1000)

        return () => clearInterval(interval)
    }, [slotId, incomePerSecond])

    async function fetchGameState() {
        if (slotId === null) return
        
        try {
            const response = await fetch(`http://localhost:8080/api/game/${slotId}`)
            const data = await response.json()
            if (data.success) {
                setSlotName(data.slotName)
                setMoney(data.money)
                setIncomePerSecond(data.incomePerSecond)
                setUpgrades(data.upgrades)
            }
            setLoading(false)
        } catch (error) {
            console.error('Error fetching game state:', error)
            setLoading(false)
        }
    }

    const buyUpgrade = useCallback(async (upgradeId: number): Promise<boolean> => {
        if (slotId === null) return false

        try {
            const response = await fetch(`http://localhost:8080/api/game/${slotId}/upgrade/${upgradeId}`, {
                method: 'POST'
            })
            const data = await response.json()
            if (data.success) {
                setMoney(data.money)
                setIncomePerSecond(data.incomePerSecond)
                // Refetch to get updated upgrade levels
                await fetchGameState()
                return true
            }
            return false
        } catch (error) {
            console.error('Error buying upgrade:', error)
            return false
        }
    }, [slotId])

    return (
        <GameContext.Provider value={{
            slotId,
            slotName,
            money,
            incomePerSecond,
            upgrades,
            loading,
            buyUpgrade,
            setSlotId
        }}>
            {children}
        </GameContext.Provider>
    )
}

export function useGame() {
    const context = useContext(GameContext)
    if (context === undefined) {
        throw new Error('useGame must be used within a GameProvider')
    }
    return context
}

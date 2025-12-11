import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import SaveSlotCard from '../../components/SaveSlotCard/SaveSlotCard'
import CreateGameForm from '../../components/CreateGameForm/CreateGameForm'
import './Title.scss'

type SaveSlot = {
    id: number;
    name: string;
    createdAt: string;
    money: string;
    playTime: string;
}

type SlotsResponse = {
    slots: SaveSlot[];
    isEmpty: boolean;
    isFull: boolean;
}

export default function Title() {
    const navigate = useNavigate()
    const [slots, setSlots] = useState<SaveSlot[]>([])
    const [isEmpty, setIsEmpty] = useState(true)
    const [isFull, setIsFull] = useState(false)
    const [showCreateForm, setShowCreateForm] = useState(false)
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        fetchSlots()
    }, [])

    async function fetchSlots() {
        try {
            const response = await fetch('http://localhost:8080/api/slots')
            const data: SlotsResponse = await response.json()
            setSlots(data.slots)
            setIsEmpty(data.isEmpty)
            setIsFull(data.isFull)
            setLoading(false)
        } catch (error) {
            console.error('Error fetching slots:', error)
            setLoading(false)
        }
    }

    async function createGame(name: string) {
        try {
            const response = await fetch('http://localhost:8080/api/slots', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name })
            })
            const data = await response.json()
            if (data.success) {
                navigate(`/world1?slot=${data.slot.id}`)
            }
        } catch (error) {
            console.error('Error creating game:', error)
        }
    }

    function playSlot(id: number) {
        navigate(`/world1?slot=${id}`)
    }

    function handleAddSlot() {
        setShowCreateForm(true)
    }

    function handleReturn() {
        setShowCreateForm(false)
    }

    if (loading) {
        return (
            <div className="title-page">
                <h1>Loading...</h1>
            </div>
        )
    }

    // First time player - show create form directly
    if (isEmpty && !showCreateForm) {
        return (
            <div className="title-page">
                <CreateGameForm onPlay={createGame} showReturn={false} />
            </div>
        )
    }

    // Creating new game from slot selection
    if (showCreateForm) {
        return (
            <div className="title-page">
                <CreateGameForm 
                    onPlay={createGame} 
                    onReturn={handleReturn}
                    showReturn={true} 
                />
            </div>
        )
    }

    // Show slot selection
    const emptySlotCount = 3 - slots.length
    const emptySlots = Array(emptySlotCount).fill(null)

    return (
        <div className="title-page">
            <h1>Incremental Game</h1>
            <div className="slots-container">
                {slots.map(slot => (
                    <SaveSlotCard 
                        key={slot.id}
                        slot={slot}
                        onPlay={playSlot}
                    />
                ))}
                {emptySlots.map((_, index) => (
                    <SaveSlotCard 
                        key={`empty-${index}`}
                        isEmpty={true}
                        onAdd={handleAddSlot}
                    />
                ))}
            </div>
        </div>
    )
}

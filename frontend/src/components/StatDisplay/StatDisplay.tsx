import { useGame } from '../../context/GameContext'
import './StatDisplay.scss'

export default function StatDisplay() {
    const { money, incomePerSecond } = useGame()

    // Format money for display
    const formattedMoney = money.toLocaleString('en-US', { 
        minimumFractionDigits: 2, 
        maximumFractionDigits: 2 
    })

    const formattedIncome = incomePerSecond.toLocaleString('en-US', {
        minimumFractionDigits: 1,
        maximumFractionDigits: 1
    })

    return (
        <div className="stat-display">
            <div className="money">
                <h1>${formattedMoney}</h1>
            </div>
            <div className="stats">
                <div className="stat income">+${formattedIncome}/s</div>
            </div>
        </div>
    );
}
import './UpgradeCard.scss'

type UpgradeCardProps = {
    title?: string;
    money?: number;
    description?: string;
    level?: number;
    income?: number;
    speed?: number;
    cost?: string;
    canAfford?: boolean;
    onBuy?: () => void;
}

export default function UpgradeCard({ 
    title = 'Title', 
    money = 0, 
    description = '',
    level = 0,
    income = 0,
    speed = 1.0,
    cost = 'Free',
    canAfford = true,
    onBuy
}: UpgradeCardProps) {
    const handleClick = () => {
        if (onBuy && canAfford) {
            onBuy()
        }
    }

    return (
        <div className="upgrade-card">
            <div className="header">
                <p className="title">{title}</p>
                <p className="money">${money}/s</p>
            </div>
            <div className="details">
                <div className="description">
                    <p>{description}</p>
                </div>
                <div className="stats">
                    <p className="level">Level: {level}</p>
                    <p className="income">Income: ${income}</p>
                    <p className="speed">Speed: {speed.toFixed(1)} seconds</p>
                </div>
            </div>
            <button 
                className={`cost ${!canAfford ? 'disabled' : ''}`}
                onClick={handleClick}
                disabled={!canAfford}
            >
                {cost}
            </button>
        </div>
    )
}
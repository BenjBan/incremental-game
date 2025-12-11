import './SaveSlotCard.scss'

type SaveSlotCardProps = {
    slot?: {
        id: number;
        name: string;
        createdAt: string;
        money: string;
        playTime: string;
    };
    onPlay?: (id: number) => void;
    onAdd?: () => void;
    isEmpty?: boolean;
}

export default function SaveSlotCard({ 
    slot, 
    onPlay, 
    onAdd, 
    isEmpty = false 
}: SaveSlotCardProps) {
    if (isEmpty || !slot) {
        return (
            <div className="save-slot-card empty" onClick={onAdd}>
                <div className="corner top-left"></div>
                <div className="corner top-right"></div>
                <div className="corner bottom-left"></div>
                <div className="corner bottom-right"></div>
                <div className="add-icon">+</div>
                <p className="add-text">Add Slot</p>
            </div>
        )
    }

    return (
        <div className="save-slot-card filled">
            <p className="slot-number">Slot {slot.id}</p>
            <p className="slot-name">{slot.name}</p>
            <div className="slot-info">
                <p>Created: {slot.createdAt}</p>
                <p>Money: {slot.money}</p>
                <p>Play Time: {slot.playTime}</p>
            </div>
            <button 
                className="play-button" 
                onClick={() => onPlay?.(slot.id)}
            >
                Play
            </button>
        </div>
    )
}

import { useState } from 'react'
import './CreateGameForm.scss'

type CreateGameFormProps = {
    onPlay: (name: string) => void;
    onReturn?: () => void;
    showReturn?: boolean;
}

export default function CreateGameForm({ 
    onPlay, 
    onReturn, 
    showReturn = false 
}: CreateGameFormProps) {
    const [name, setName] = useState('')

    const handleSubmit = () => {
        const gameName = name.trim() || 'My First World'
        onPlay(gameName)
    }

    return (
        <div className="create-game-form">
            <h1>Create New Game</h1>
            <div className="form-field">
                <label htmlFor="game-name">Name:</label>
                <input 
                    type="text" 
                    id="game-name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="My First World"
                />
            </div>
            <div className="form-buttons">
                <button 
                    className="play-button" 
                    onClick={handleSubmit}
                >
                    Play
                </button>
                {showReturn && (
                    <button 
                        className="return-button" 
                        onClick={onReturn}
                    >
                        Return
                    </button>
                )}
            </div>
        </div>
    )
}

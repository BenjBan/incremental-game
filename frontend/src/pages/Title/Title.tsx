import { useNavigate } from 'react-router-dom'
import './Title.scss'

export default function Title() {
    const navigate = useNavigate()

    function play() {
        navigate('/world1')
    }

    return (
        <div className="title-page">
            <div>
                <h1>Incremental Game</h1>
            </div>
            <div>
                <button onClick={play} className="play-button">Play</button>
            </div>
        </div>
    )
}

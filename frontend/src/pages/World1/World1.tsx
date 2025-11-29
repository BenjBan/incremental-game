import { useNavigate } from 'react-router-dom'
import TopNavBar from '../../components/TopNavBar/TopNavBar'
import BottomNavBar from '../../components/BottomNavBar/BottomNavBar'
import './World1.scss'

export default function World1() {
    const navigate = useNavigate()

    function back() {
        navigate('/')
    }

    return (
        <>
            <header>
                <TopNavBar />
            </header>
            <div className="stats-display">
            </div>
            <div className="content">
                {/* temporary button for testing */}
                <div style={{ padding: '20px', textAlign: 'center' }}>
                    <button onClick={back} className="play-button">Return</button>
                </div>
            </div>
            <footer>
                <BottomNavBar />
            </footer>
        </>
    )
}

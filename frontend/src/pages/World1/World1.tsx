import { useNavigate } from 'react-router-dom'
import TopNavBar from '../../components/TopNavBar/TopNavBar'
import './World1.scss'

export default function World1() {
    const navigate = useNavigate()

    function back() {
        navigate('/')
    }

    return (
        <>
            <TopNavBar />
            <div style={{ padding: '20px', textAlign: 'center' }}>
                <button onClick={back} className="play-button">Return</button>
            </div>
        </>
    )
}

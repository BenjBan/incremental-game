import { useNavigate } from 'react-router-dom'
import TopNavBar from '../../components/TopNavBar/TopNavBar'
import StatDisplay from '../../components/StatDisplay/StatDisplay'
import Carousel from '../../components/Carousel/Carousel'
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
                <StatDisplay />
            </div>
            <div className="content">
                <Carousel />
            </div>
            <footer>
                <BottomNavBar />
            </footer>
        </>
    )
}

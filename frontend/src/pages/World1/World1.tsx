import { useEffect } from 'react'
import { useNavigate, useSearchParams } from 'react-router-dom'
import { useGame } from '../../context/GameContext'
import TopNavBar from '../../components/TopNavBar/TopNavBar'
import StatDisplay from '../../components/StatDisplay/StatDisplay'
import Carousel from '../../components/Carousel/Carousel'
import BottomNavBar from '../../components/BottomNavBar/BottomNavBar'

import './World1.scss'

export default function World1() {
    const navigate = useNavigate()
    const [searchParams] = useSearchParams()
    const { setSlotId, loading, slotId } = useGame()

    useEffect(() => {
        const slotParam = searchParams.get('slot')
        if (slotParam) {
            setSlotId(parseInt(slotParam, 10))
        } else {
            // No slot specified, go back to title
            navigate('/')
        }
    }, [searchParams, setSlotId, navigate])

    if (loading || slotId === null) {
        return (
            <div className="loading">
                <h1>Loading...</h1>
            </div>
        )
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

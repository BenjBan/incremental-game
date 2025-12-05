import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import TopNavBar from '../../components/TopNavBar/TopNavBar'
import StatDisplay from '../../components/StatDisplay/StatDisplay'
import Carousel from '../../components/Carousel/Carousel'
import BottomNavBar from '../../components/BottomNavBar/BottomNavBar'

import './World1.scss'

export default function World1() {
    const navigate = useNavigate()
    const [message, setMessage] = useState('')

    function back() {
        navigate('/')
    }

    async function callBackend() {
        try {
            const response = await fetch('http://localhost:8080/api/click')
            const data = await response.json()
            setMessage(data.message)
            console.log(data)
        } catch (error) {
            console.error('Error connecting to backend:', error)
            setMessage('Error connecting to backend')
        }
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
                <div style={{ textAlign: 'center', marginBottom: '1rem' }}>
                    <button onClick={callBackend} style={{ padding: '10px 20px', fontSize: '16px', cursor: 'pointer' }}>
                        Call Java Backend
                    </button>
                    {message && <p style={{ color: 'white', marginTop: '10px' }}>{message}</p>}
                </div>
                <Carousel />
            </div>
            <footer>
                <BottomNavBar />
            </footer>
        </>
    )
}

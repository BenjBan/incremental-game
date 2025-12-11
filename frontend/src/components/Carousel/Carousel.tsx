import { useRef, useState, useEffect, MouseEvent } from 'react'
import { useGame } from '../../context/GameContext'
import './Carousel.scss'
import UpgradeCard from '../UpgradeCard/UpgradeCard'

export default function Carousel() {
    const carouselRef = useRef<HTMLDivElement>(null)
    const [isDragging, setIsDragging] = useState(false)
    const [startX, setStartX] = useState(0)
    const [scrollLeft, setScrollLeft] = useState(0)
    const { upgrades, money, buyUpgrade } = useGame()

    useEffect(() => {
        const carousel = carouselRef.current
        if (!carousel) return

        const handleWheel = (e: WheelEvent) => {
            if (e.deltaY !== 0) {
                // If vertical scroll is detected, translate it to horizontal scroll
                // We don't prevent default if it's a trackpad (often deltaX is present)
                // But for a vertical mouse wheel, deltaX is 0.
                if (Math.abs(e.deltaY) > Math.abs(e.deltaX)) {
                    carousel.scrollLeft += e.deltaY
                    e.preventDefault()
                }
            }
        }

        carousel.addEventListener('wheel', handleWheel, { passive: false })

        return () => {
            carousel.removeEventListener('wheel', handleWheel)
        }
    }, [])

    const handleMouseDown = (e: MouseEvent<HTMLDivElement>) => {
        if (!carouselRef.current) return
        setIsDragging(true)
        setStartX(e.pageX - carouselRef.current.offsetLeft)
        setScrollLeft(carouselRef.current.scrollLeft)
    }

    const handleMouseLeave = () => {
        setIsDragging(false)
    }

    const handleMouseUp = () => {
        setIsDragging(false)
    }

    const handleMouseMove = (e: MouseEvent<HTMLDivElement>) => {
        if (!isDragging || !carouselRef.current) return
        e.preventDefault()
        const x = e.pageX - carouselRef.current.offsetLeft
        const walk = (x - startX) * 2 // Scroll-fast multiplier
        carouselRef.current.scrollLeft = scrollLeft - walk
    }

    const handleBuyUpgrade = async (upgradeId: number) => {
        await buyUpgrade(upgradeId)
    }

    return (
        <div 
            className={`carousel ${isDragging ? 'is-dragging' : ''}`}
            ref={carouselRef}
            onMouseDown={handleMouseDown}
            onMouseLeave={handleMouseLeave}
            onMouseUp={handleMouseUp}
            onMouseMove={handleMouseMove}
        >
            {upgrades.map(upgrade => (
                <UpgradeCard 
                    key={upgrade.id}
                    title={upgrade.name}
                    money={upgrade.incomePerSecond}
                    description={upgrade.description}
                    level={upgrade.level}
                    income={upgrade.income}
                    speed={upgrade.speed}
                    cost={upgrade.costDisplay}
                    canAfford={money >= upgrade.cost}
                    onBuy={() => handleBuyUpgrade(upgrade.id)}
                />
            ))}
        </div>
    )
}
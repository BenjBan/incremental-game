import { useRef, useState, useEffect, MouseEvent } from 'react'
import './Carousel.scss'
import UpgradeCard from '../UpgradeCard/UpgradeCard'

export default function Carousel() {
    const carouselRef = useRef<HTMLDivElement>(null)
    const [isDragging, setIsDragging] = useState(false)
    const [startX, setStartX] = useState(0)
    const [scrollLeft, setScrollLeft] = useState(0)

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

    return (
        <div 
            className={`carousel ${isDragging ? 'is-dragging' : ''}`}
            ref={carouselRef}
            onMouseDown={handleMouseDown}
            onMouseLeave={handleMouseLeave}
            onMouseUp={handleMouseUp}
            onMouseMove={handleMouseMove}
        >
            <UpgradeCard title="Upgrade 1" money={0} 
            description="Automatically gain +$1 per second" 
            level={1} income={1} speed={1} cost="Free"/>
            
            <UpgradeCard title="Upgrade 2" money={0} 
            description="Automatically gain +$2 per second" 
            level={1} income={2} speed={1} cost="Free"/>
            
            <UpgradeCard title="Upgrade 3" money={0} 
            description="Automatically gain +$3 per second" 
            level={1} income={3} speed={1} cost="Free"/>
            
            <UpgradeCard title="Upgrade 4" money={0} 
            description="Automatically gain +$4 per second" 
            level={1} income={4} speed={1} cost="Free"/>
            
            <UpgradeCard title="Upgrade 5" money={0} 
            description="Automatically gain +$5 per second" 
            level={1} income={5} speed={1} cost="Free"/>
            
            <UpgradeCard title="Upgrade 6" money={0} 
            description="Automatically gain +$6 per second" 
            level={1} income={6} speed={1} cost="Free"/>
            
            <UpgradeCard title="Upgrade 7" money={0} 
            description="Automatically gain +$7 per second" 
            level={1} income={7} speed={1} cost="Free"/>
        </div>
    )
}
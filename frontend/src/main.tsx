import React from 'react'
import ReactDOM from 'react-dom/client'
import TopNavBar from './components/TopNavBar'
import './css/global.css'
import './css/index.css'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <TopNavBar />
    <div style={{ padding: '20px' }}>
        <h1>Welcome to the Incremental Game</h1>
        <p>This is now running with React + Vite!</p>
    </div>
  </React.StrictMode>,
)

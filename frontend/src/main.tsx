import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Title from './pages/Title/Title'
import World1 from './pages/World1/World1'
import './global.scss'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Title />} />
        <Route path="/world1" element={<World1 />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>,
)

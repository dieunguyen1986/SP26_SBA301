import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// import './index.css'
// import App from './app/App.jsx'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import PublicHomePage from './features/public-site/pages/PublicHomePage.jsx';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <PublicHomePage />
  </StrictMode>,
)

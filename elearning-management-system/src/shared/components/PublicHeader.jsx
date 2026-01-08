import React from 'react'
import logo from "@/assets/logo.svg";
import "@/styles/Header.css"

export default function PublicHeader() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-white flex align-content-center" style={{
            height: "112px",
            maxWidth: "1280px",
            margin: "0 auto",
        }}>
            <div className="container-fluid">
                <a className="navbar-brand" href="#">
                    <img src={logo} alt="Logo" width="160" height="50" />
                </a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div id='nav-bar-custom' className="navbar-nav">
                        <a className="nav-link active" aria-current="page" href="#">Home</a>
                        <a className="nav-link" href="#">Courses</a>
                        <a className="nav-link" href="#">Mentor</a>
                        <a className="nav-link" href="#">Group</a>
                        <a className="nav-link" href="#">Testimonial</a>
                        <a className="nav-link" href="#">Docs</a>
                    </div>
                </div>
                <div className='auth'>
                    <button className='' style={{
                        backgroundColor: "rgb(101,86,255)",
                        color: "white",
                        padding: "20px 64px",
                        borderRadius: "999px",
                        border: "none",
                    }}>Sign In</button>
                    <button style={{
                        backgroundColor: "rgba(101, 86, 255, .15)",
                        color: "#6556ff",
                        padding: "20px 64px",
                        borderRadius: "999px",
                        border: "none",
                        marginLeft: "1rem",
                    }}>Sign Up</button>
                </div>
            </div>
        </nav>
    )
}

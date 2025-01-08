import React from "react";
import "../../css/Footer.css";

const Footer = () => {
    const currentYear = new Date().getFullYear();

    return (
        <footer className="footer">
            <div className="footer-content">
                <p>&copy; {currentYear} My Bulletin Board. All rights reserved.</p>
                <ul className="footer-links">
                    <li>
                        <a href="/about">About</a>
                    </li>
                    <li>
                        <a href="/contact">Contact</a>
                    </li>
                    <li>
                        <a href="/privacy">Privacy Policy</a>
                    </li>
                </ul>
            </div>
        </footer>
    );
};

export default Footer;
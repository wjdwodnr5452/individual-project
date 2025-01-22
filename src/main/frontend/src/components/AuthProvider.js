import React, { createContext, useContext, useState, useEffect } from "react";
import { checkLoginStatus } from "../api/authService"; // authService에서 함수 가져오기

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchLoginStatus = async () => {
            const status = await checkLoginStatus();
            setIsLoggedIn(status.isLoggedIn);
            setUser(status.user || null);
        };
        fetchLoginStatus();
    }, []);

    return (
        <AuthContext.Provider value={{ isLoggedIn, user, setIsLoggedIn, setUser }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);

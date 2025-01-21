export const checkLoginStatus = async () => {
    try {
        const response = await fetch(`${process.env.REACT_APP_API_URL}/api/login/status`, {
            method: "GET",
            credentials: "include", // 쿠키를 포함하여 요청
            headers: {
                "Content-Type": "application/json",
            },
        });

        if (response.ok) {
            const data = await response.json();
            return { isLoggedIn: true, user: data.user };
        } else {
            return { isLoggedIn: false, user: null };
        }
    } catch (error) {
        console.error("Error checking login status:", error);
        return { isLoggedIn: false, user: null };
    }
};

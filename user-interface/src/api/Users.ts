import axios from "axios";

export const signUp = async (username: string, password: string, role: string) => {
    try {
        const response = await axios.post('http://localhost:8080/api/v1/auth/signup', {username, password, role})
    } catch (e) {
        alert(e);
    }
}

export const signIn = async (username: string, password: string) => {
    try {
        const response = await axios.post('http://localhost:8080/api/v1/auth/login', {username, password})
        localStorage.setItem('token', response.data.accessToken);
        console.log(response.data.accessToken);
    } catch (e) {
        alert(e);
    }
}

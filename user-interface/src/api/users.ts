import axios from "axios";

export const signIn = async (username: string, password: string, role: string) => {
    try {
        const response = await axios.post('http://localhost:8080/api/v1/users/', {username, password, role})
    } catch (e) {
        alert(e);
    }
}
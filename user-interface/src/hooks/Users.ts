import {useEffect, useState} from "react";
import {IUser} from "../models";
import axios, {AxiosError} from "axios";

export function useUsers() {
    const [users, setUsers] = useState<IUser[]>([])
    const [loading, setLoading] = useState(false)

    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem('token')}`,
        },
    };

    async function getUsers() {
        try {
            setLoading(true);
            console.log(config);
            const response = await axios.get<IUser[]>('http://localhost:8080/api/v1/users/', config)
            setUsers(response.data);
            setLoading(false);
        } catch (e: unknown) {
            const error = e as AxiosError;
            alert(error.message)
            setLoading(false);
        }
    }

    useEffect(() => {
        getUsers();
    }, [])

    return {users};
}

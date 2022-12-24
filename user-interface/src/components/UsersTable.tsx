import {useUsers} from "../hooks/Users";
import {IUser} from "../models";

interface UserProps {
    user: IUser;
}

export function UsersTable({user}: UserProps) {
    return (
        <tr>
            <td>{user.id}</td>
            <td>{user.username}</td>
            <button>Add to friends</button>
        </tr>
    )
}
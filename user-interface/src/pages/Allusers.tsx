import {UsersTable} from "../components/UsersTable";
import React from "react";
import {useUsers} from "../hooks/Users";

export function Allusers() {
    const {users} = useUsers();

    return (
        <div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Username</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.map(user => <UsersTable user={user} key={user.id}/>)}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
import {useState} from "react";
import {signIn} from "../api/users";

export function SignUp() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [role, setRole] = useState('')

    return (
        <form action="">
            <div>
                <label htmlFor="username">Username</label>
                <input type="text" id="username" onChange={e => setUsername(e.target.value)}/>
            </div>

            <div>
                <label htmlFor="username">Password</label>
                <input type="password" id="password" onChange={e => setPassword(e.target.value)}/>
            </div>

            <div>
                <label htmlFor="role">Role</label>
                <select name="role"
                        onChange={e => setRole(e.target.value)}>
                    <option value="ADMIN">ADMIN</option>
                    <option value="USER">USER</option>
                </select>
            </div>

            <div>
                <button onClick={() => signIn(username, password, role)}>SignUp</button>
            </div>
        </form>
    )
}
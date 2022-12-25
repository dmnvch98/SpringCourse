import {useState} from "react";
import {signUp} from "../api/users";

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
                <input type="text" id="role" onChange={e => setRole(e.target.value)}/>
            </div>

            <div>
                <button onClick={() => signUp(username, password, role)}>SignUp</button>
            </div>
        </form>
    )
}
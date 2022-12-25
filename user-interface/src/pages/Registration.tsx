import {
    Button,
    Container,
    Grid,
    IconButton,
    Paper,
    TextField
} from "@mui/material";
import InputAdornment from '@mui/material/InputAdornment';
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import {useState} from "react";
import {signIn, signUp} from "../api/Users";


export function Registration() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [role, setRole] = useState('')

    const registration = () => {
        signUp(username, password, role)
            .then(() => signIn(username, password))
            .then(() => window.location.replace('http://localhost:3000/users'))
            .catch(e => alert(e));
    }

    const [values, setValues] = useState({
        username: "",
        password: "",
        showPassword: false
    })

    const handlePassVisibility = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword
        })
    }

    return (
        <>
            <Container maxWidth="sm">
                <Grid
                    container
                    spacing={2}
                    direction="column"
                    justifyContent="center"
                    style={{
                        minHeight: "100vh"
                    }}
                >
                    <Paper elevation={2} sx={{padding: 5}}>
                        <h2>Login</h2>
                        <Grid
                            container
                            direction="column"
                            spacing={2}>
                            <Grid item>
                                <TextField
                                    id="standard-basic"
                                    label="Username"
                                    variant="outlined"
                                    fullWidth
                                    required
                                    onChange={e => setUsername(e.target.value)}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    id="standard-basic"
                                    label="Password"
                                    variant="outlined"
                                    type={values.showPassword ? "text" : "password"}
                                    fullWidth
                                    required
                                    onChange={e => setPassword(e.target.value)}
                                    InputProps={{
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    onClick={handlePassVisibility}
                                                    aria-label="toggle password"
                                                    edge="end"
                                                >
                                                    {values.showPassword ? <VisibilityOffIcon /> : <VisibilityIcon />}
                                                </IconButton>
                                            </InputAdornment>
                                        ),
                                    }}/>
                            </Grid>
                            <Grid item>
                                <TextField
                                    id="standard-basic"
                                    label="Role"
                                    variant="outlined"
                                    fullWidth
                                    required
                                    onChange={e => setRole(e.target.value)}
                                />
                            </Grid>
                            <Grid item>
                                <Button
                                    variant="contained"
                                    fullWidth
                                    onClick={registration}>Submit</Button>
                            </Grid>
                        </Grid>
                    </Paper>

                </Grid>
            </Container>
        </>
    )
}
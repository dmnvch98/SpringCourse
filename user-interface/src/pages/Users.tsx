import {useUsers} from "../hooks/Users";
import {
    Button,
    Container,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from "@mui/material";

export function Users() {
    const {users} = useUsers();

    return (
        <>
            <Container maxWidth="md">
                <TableContainer component={Paper}>
                    <Table sx={{minWidth: 650}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>id</TableCell>
                                <TableCell>Username</TableCell>
                                <TableCell></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {users.map((user) => (
                                <TableRow>
                                    <TableCell component="th" scope="row">
                                        {user.id}
                                    </TableCell>
                                    <TableCell component="th" scope="row">
                                        {user.username}
                                    </TableCell>
                                    <TableCell component="th" scope="row" align="right">
                                        <Button variant="contained">Add to Friend</Button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Container>
        </>
    )
}
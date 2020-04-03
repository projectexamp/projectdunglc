import { Role } from './role';

export class User {
    userID :string;
    userName: string;
    password: string;
    fullName: string;
    gender : number;
    status:number;
    role: Role[];
}

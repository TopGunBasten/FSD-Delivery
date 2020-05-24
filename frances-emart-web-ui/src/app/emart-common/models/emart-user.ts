export class EmartUser {
    id: string;
    userName: string;
    password: string;
    jwtToken: string;
    roles: string[];

    public isBuyer(): boolean {
        for (const index  in this.roles) {
            if (this.roles[index] === 'BUYER') { return true; }
        }
        return false;
    }

    public isSeller(): boolean {
        for (const index  in this.roles) {
            if (this.roles[index] === 'SELLER') { return true; }
        }
        return false;
    }
}

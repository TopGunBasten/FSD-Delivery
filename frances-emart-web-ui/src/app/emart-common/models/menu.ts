export class Menu {
    name: string;
    isActive: boolean;
    path: string;

    constructor(name: string,
                isActive: boolean,
                path: string) {
        this.name = name;
        this.isActive = isActive;
        this.path = path;
    }
}

export class CarDetails {
    carDetails_id?: number;
    color?: string;
    weight?: number;
    hight?: number;

    constructor(cd: any) {
        this.carDetails_id = cd.carDetails_id;
        this.color = cd.color;
        this.weight = cd.weight;
        this.hight = cd.hight;
    }
}
export class CarDetails {
    carDetails_id?: number;
    color?: string;
    weight?: number;
    high?: number;

    constructor(cd: any) {
        this.carDetails_id = cd.carDetails_id;
        this.color = cd.color;
        this.weight = cd.weight;
        this.high = cd.high;
    }
}
export class Order {
    clientId:number;
    carId:number;
    employeeMail:string;
    startDate = new Date();
    endDate = new Date();

    setCientId(clientId){
        this.clientId = clientId;
    }
    setcarId(carId){
        this.carId = carId;
    }
    setEmployeeMail(employeeMail){
        this.employeeMail = employeeMail;
    }

    setStartDate(startDate){
        this.startDate = startDate;
    }

    setEndDate(endDate){
        this.endDate = endDate;
    }
}

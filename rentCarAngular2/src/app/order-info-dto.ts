export class OrderInfoDTO {
    orderId:number;
    carBrandName:string;
    clientName: string;
    administrationName:string;
    dateOfReturn = new Date();
    dateOfRental = new Date();
    
    setOrderId(orderId){
        this.orderId = orderId;
    }

    setCarBrandName(carBrandName){
        this.carBrandName = carBrandName;
    }

    setClientName(clientName){
        this.clientName = clientName;
    }

    setAdministrationName(administrationName){
        this.administrationName = administrationName;
    }

    setDateOfRental(dateOfRental){
        this.dateOfRental = dateOfRental;
    }

    setDateOfReturn(dateOfReturn){
        this.dateOfReturn = dateOfReturn;
    }
}

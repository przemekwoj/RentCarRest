export class Attachment {
    fileName:string;
    attachment_encoded_b64:string;

    constructor(fileName:string,attachment_encoded_b64:string){
        this.attachment_encoded_b64 = attachment_encoded_b64;
        this.fileName = fileName;
    }

    setFielName(fileName){
        this.fileName = fileName;
    }

    setAttachmentEncodedB64(attachment_encoded_b64){
        this.attachment_encoded_b64 = attachment_encoded_b64;
    }

}

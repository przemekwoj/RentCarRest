import { Attachment } from './attachment';
import { QueueType } from './queue-type.enum';

export class EmailSend {
    receivers:QueueType;
    subject:string;
    message:string;
    attachments:Array<Attachment> = [];

    setReceivers(receivers){
        this.receivers = receivers;
    }
    setMessage(message){
        this.message = message;
    }

    setAttachments(attachments){
        this.attachments = attachments;
    }

    setSubject(subject){
        this.subject = subject;
    }
}

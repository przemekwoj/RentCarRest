import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { EmailSend } from '../email-send';
import { EmailRepositoryService } from '../services/email-repository.service';
import { Attachment } from '../attachment';
import { QueueType } from '../queue-type.enum';
import { AuthenticationServiceService } from '../services/authentication-service.service';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss']
})
export class EmailComponent implements OnInit {
  keys = Object.keys;
  queueType = QueueType;
  
  attachments:Array<Attachment> = [];
  fileBlob:Blob = null;
  base64File: string;
  fileToUpload: File = null;
  emailSend: EmailSend = new EmailSend();

  emailInfo  = new FormGroup({
    subject: new FormControl('subject'),
    message: new FormControl(''),
    receivers: new FormControl(QueueType.NOTIFY_ALL),
  });
  constructor(private emailRepository: EmailRepositoryService,private authentcationService: AuthenticationServiceService) {
   }

  ngOnInit() {
  }

 changeFile(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });
}

uploadFile(event) {
    if (event.target.value) {
        const file = event.target.files[0];
        const type = file.type;
        console.log(file);
        this.changeFile(file).then((base64: string): any => {
          this.base64File = base64;
          console.log(base64);
       this.attachments.push(new Attachment(file.name,base64));
        });
    } else alert('Nothing')
}

removeAttachemnt(i:number){
  console.log("delete attachemnt"+i)
  this.attachments.splice(i,1);
}

  async sendEmail() {
    this.emailSend.message = this.emailInfo.value.message;
    this.emailSend.subject = this.emailInfo.value.subject;
    this.emailSend.receivers = this.emailInfo.value.receivers;

   // this.emailSend.attachments = this.emailInfo.value.oneFile;
   this.emailSend.setAttachments(this.attachments);
    console.log(this.emailInfo);
    this.emailRepository.postEmail(this.emailSend).subscribe(email => { 
      console.log('poszlo postem');
      console.log(email); });
    //wait till save car in DB
    // await this.delay(500);
    console.log(this.emailSend);
    this.emailInfo.reset({
      subject: 'subject',
      message: '',
      receivers: QueueType.NOTIFY_ALL
    });
      console.log(this.emailSend);
      this.attachments = [];
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from '../services/authentication-service.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.scss']
})
export class LogoutComponent implements OnInit {

  constructor(private router: Router,private authenticationService: AuthenticationServiceService) { }

  ngOnInit() {
  }

  logout() {
    this.authenticationService.changeAuth(false);
    this.authenticationService.changeRole('');
    this.authenticationService.changeEmail('');
    this.authenticationService.changePassword('');
    this.router.navigateByUrl('/').then(() => {
      window.location.reload();
    });
  }
}

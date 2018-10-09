import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService } from 'primeng/primeng';
import { AuthenticationService } from '../../services/authentication.service';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoginDialogVisible = false;
  @ViewChild('login') loginElement: LoginDialogComponent;

  constructor(private authenticationService: AuthenticationService, private messageService: MessageService) { }

  ngOnInit() {
  }

  showDialog() {
    this.isLoginDialogVisible = !this.isLoginDialogVisible;
    this.loginElement.show();
  }

  logout() {
    this.authenticationService.logout();
    this.buttonStyleHandler();
    this.messageService.add({ key: 'toaster', severity: 'success', summary: 'Success Message', detail: 'You have been logged out successfully.' });
  }


  buttonStyleHandler() {
    document.getElementById('login-button').style.cssText = "display: block";
    document.getElementById('logout-button').style.cssText = "display: none";
    document.getElementById('reverse-button').style.cssText = "display: none";
  }
}

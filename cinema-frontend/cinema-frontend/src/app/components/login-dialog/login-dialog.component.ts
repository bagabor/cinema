import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { MessageService } from 'primeng/primeng';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent implements OnInit {

  isVisible: boolean = false;
  private username: string
  private password: string
  private Title = "Login"

  constructor(private authenticationService: AuthenticationService, private messageService: MessageService) { }

  ngOnInit() { }

  loginHandleClick(event) {
    this.authenticationService.login(this.username, this.password).subscribe(
      response => {
        this.isVisible = false;
        this.buttonStyleHandler();
      },
      err => {
        this.messageService.add({ key: 'toaster', severity: 'error', summary: 'Error Message', detail: 'Wrong username or password.' });
        () => { this.clearFields(); }
      });
  }

  buttonStyleHandler() {
    document.getElementById('login-button').style.cssText = "display: none";
    document.getElementById('logout-button').style.cssText = "display: block";
    document.getElementById('reverse-button').style.cssText = "display: block";    
  }

  clearFields() {
    this.username = '';
    this.password = '';
  }

  public show() {
    this.isVisible = true;
  }

}

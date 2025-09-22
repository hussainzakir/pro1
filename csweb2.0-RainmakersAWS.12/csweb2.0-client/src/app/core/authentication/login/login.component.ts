import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/authentication/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title: string;
  form: FormGroup;
  public loginInvalid: boolean;
  public isLoggedIn: boolean;
  private formSubmitAttempt: boolean;
  private returnUrl: string;
  errorMessage: string;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {
  }

  ngOnInit() {
    console.log('return url: '+ this.route.snapshot.queryParams['returnUrl']);
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/dashboard';
    this.title = 'Login to CSWeb';

    this.form = this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });

    this.authService.isAuthenticated().subscribe(
      res => {
        //user is authenticated, reroute
        this.router.navigateByUrl(this.returnUrl);
      },
      err => console.log('User not authenticated')
    );
  }

  onSubmit(): void {
    const username = this.form.get('username').value;
    const password = this.form.get('password').value;
    if (this.form.valid) {
      this.isLoading = true;
      this.authService.login(username, password).subscribe(
        data => {
          this.isLoading = false;
          this.loginInvalid = false;
          this.isLoggedIn = true;
          localStorage.setItem('currentUser', JSON.stringify(data));
          // this.roles = this.tokenStorage.getUser().roles;
          //this.reloadPage();
          this.router.navigateByUrl(this.returnUrl);
        },
        err => {
          this.isLoading = false;
          this.errorMessage = err.messages.join(", ");
          this.loginInvalid = true;
          this.isLoggedIn = false;
          console.log(err);
        }
      );
    } else{
      this.formSubmitAttempt = true;
    }
  }

  reloadPage(): void {
    window.location.reload();
  }
}

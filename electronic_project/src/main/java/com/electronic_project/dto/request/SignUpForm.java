package com.electronic_project.dto.request;

import com.electronic_project.model.user.User;
import org.springframework.validation.Errors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SignUpForm {
    @NotBlank(message = "Vui lòng nhập tên đăng nhập")
    @Size(min = 6, max = 30, message = "Tên đăng nhập phải có ít nhất 6 ký tự tối đa 30 ký tự")
    private String username;
    @NotBlank(message = "Vui lòng nhập email")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không đúng định dạng. Ví dụ: your_email@domain.com")
    private String email;
    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Size(min = 6, max = 30, message = "Mật khẩu phải có ít nhất 6 ký tự tối đa 30 ký tự")
    private String password;
    @NotBlank(message = "Vui lòng nhập xác nhận lại mật khẩu")
    private String confirmPassword;
    private Set<String> roles;

    public SignUpForm() {
    }

    public SignUpForm(String username, String email, String password, String confirmPassword, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
  public void validate(List<User> list, SignUpForm signInForm, Errors errors){
       for (User user : list) {
           if (Objects.equals(user.getUsername(), signInForm.getUsername())) {
               errors.rejectValue("username", "username", "Tên đăng nhập " + signInForm.getUsername() + " đã được sử dụng");
           }
           if (Objects.equals(user.getEmail(), signInForm.getEmail())) {
               errors.rejectValue("email", "email", "Email " + signInForm.getEmail() + " đã được sử dụng");
           }
       }
       if (signInForm.getPassword() != null && signInForm.getConfirmPassword() != null) {
           if (!signInForm.getPassword().equals(signInForm.getConfirmPassword())){
               errors.rejectValue("confirmPassword", "confirmPassword", "Mật khẩu nhập lại không đúng");
           }
       }

   }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

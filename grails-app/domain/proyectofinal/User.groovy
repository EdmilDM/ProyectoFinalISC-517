package proyectofinal

class User {
    String username
    String password
    String email

    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static hasMany = [ authorities: UserRole ]

    transient springSecurityService

    static constraints = {
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this)*.role
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ?
                springSecurityService.encodePassword(password) :
                password
    }

    static mapping = {
        password column: '`password`'
    }

    static transients = ['springSecurityService']
}

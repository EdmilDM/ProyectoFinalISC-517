package proyectofinal

class User {
    String username
    String password
    String email
    String complete_name
    boolean is_entity

    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static hasMany = [ authorities: UserRole, sales: Sale ]

    transient springSecurityService

    static constraints = {
        username blank: false, unique: true
        password size: 5..15
        complete_name blank: false
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

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
    boolean enabled = true

    static hasMany = [ authorities: UserRole, sales: Sale ]
    static hasOne = [ cart: Cart ]

    transient springSecurityService

    static constraints = {
        complete_name blank: false
        password blank: false, password: true
        username blank: false, unique: true
        email blank: false, unique: true
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

    static transients = ['springSecurityService']

    static mapping = {
        password column: '`password`'
    }

    def isAdmin( ){
        return this.authorities.contains( Role.findByAuthority( "ROLE_ADMIN" ) )
    }

}

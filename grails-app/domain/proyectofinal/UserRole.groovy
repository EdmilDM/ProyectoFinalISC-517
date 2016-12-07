package proyectofinal

import grails.gorm.DetachedCriteria
import org.apache.commons.lang.builder.HashCodeBuilder

class UserRole implements Serializable {
    User user
    Role role

    @Override
    boolean equals(other) {
        if (other instanceof UserRole) {
            other.userId == user?.id && other.roleId == role?.id
        }
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (role) builder.append(role.id)
        builder.toHashCode()
    }

    static UserRole get(long userId, long roleId) {
        criteriaFor(userId, roleId).get()
    }

    static boolean exists(long userId, long roleId) {
        criteriaFor(userId, roleId).count()
    }

    private static DetachedCriteria criteriaFor(long userId, long roleId) {
        UserRole.where {
            user == Usuario.load(userId) &&
                    role == Role.load(roleId)
        }
    }

    static UserRole create( User user, Role role ) {
        new UserRole( user: user, role: role ).save( )
    }

    static boolean remove( User u, Role r ) {
        if (u != null && r != null) {
            UserRole.where { user == u && role == r }.deleteAll()
        }
    }

    static int removeAll( User u ) {
        u == null ? 0 : UserRole.where { user == u }.deleteAll()
    }

    static int removeAll( Role r ) {
        r == null ? 0 : UserRole.where { role == r }.deleteAll()
    }
}

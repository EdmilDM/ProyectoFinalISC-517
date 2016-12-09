// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'proyectofinal.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'proyectofinal.UserRole'
grails.plugin.springsecurity.authority.className = 'isc517p13.Role'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/shutdown',       access: ['permitAll']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**',      filters: 'none'],
        [pattern: '/**/js/**',       filters: 'none'],
        [pattern: '/**/css/**',      filters: 'none'],
        [pattern: '/**/images/**',   filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.securityConfigType = "Annotation"

//Para permitir el acceso sin la necesidad de reglas.
grails.plugin.springsecurity.rejectIfNoRule = false
grails.plugin.springsecurity.fii.rejectPublicInvocations = false

grails.plugin.springsecurity.logout.postOnly = false

//PAYPAL STUFF
paypal.email="test@servidoresactivos.com"
paypal.sandbox.clientId = 'AfSGojq0W8kM7ABw-gKQmFK8syX8WbyR9PuCA5qmo_yQyKkWYWpMrMkmosAYG6zoZ9Dir5hJdcd8DO3q'
paypal.sandbox.clientSecret = 'EKrCz9NSMVACbzyOs5hVuH4ozfnBBiEhO6caQFZNTJu7to5dgkFjaJLyAx6i5cxRaM097Hf8Omp4bF-Q'
paypal.sandbox.endpoint = "https://api.sandbox.paypal.com"
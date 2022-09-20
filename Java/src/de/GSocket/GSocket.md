# GSocket  
| Function                               | Internally Used |
| -------------------------------------- | --------------- | 
| GSocket.Initialize()                   | No              |
| GSocket.LoadPackages((String) Package) | Yes             |

| Setting      | Type    | Default     |
| ------------ | ------- | ----------- |
| GSocket.IP   | String  | "localhost" |
| GSocket.Port | Integer | 5000        |

## GSocket.LoadPackages((String) Package) (internally used)  
returns a Set of classes which have been found in the targeted package  
| Returns      |
| ------------ |
| Set< Class > |

## GSocket.Initialize()  
Initializes and creates a udp server  

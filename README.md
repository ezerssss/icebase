Simple Digital Commerce Platform integrated with a primitive database made from scratch. Users can create and log in to stored accounts where they can both buy and sell items.

# Key Features

## Log in / Sign up
Users can both log in and register to the app. When they create an account they
can then log in to access the app. The registered users are stored locally, so
when they close the app, they can still use the same credentials to log in.

## Selling
Users can sell items to their own stores and can gain money when another
account buys their items.

## Buying
Users can browse different stores and buy the items listed in the store. A cart
system will also be present allowing multiple items to be bought at once per
store. When the user finishes a transaction, all necessary fields will be updated
(stock of the item, money of buyer and seller, etc.).

## Database
All interactions that need to be saved are implemented using a primitive database
made from scratch. It will also handle the login and signup processes. Updating,
filtering, and other database actions (CRUD) will be abstracted to its own
class(es).

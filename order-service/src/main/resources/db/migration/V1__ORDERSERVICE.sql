insert into order_status (id, name, description) values (1, 'Pending', 'customer started the checkout process but did not complete');
insert into order_status (id, name, description) values (2, 'Awaiting Payment', 'customer has completed the checkout process, but payment has yet to be confirmed. Authorize only transactions that are not yet captured have this status');
insert into order_status (id, name, description) values (3, 'Awaiting Fulfillment', 'customer has completed the checkout process and payment has been confirmed');
insert into order_status (id, name, description) values (4, 'Refunded', 'seller has used the Refund action');

insert into order_item_status (id, name, description) values (1, 'Pending', 'customer started the checkout process but did not complete');
insert into order_item_status (id, name, description) values (2, 'Awaiting Shipment', 'item has been pulled and packaged and is awaiting collection from a shipping provider');
insert into order_item_status (id, name, description) values (3, 'Completed', 'item has been shipped/picked up, and receipt is confirmed; an Authorize only transaction has been captured; client has paid for their digital product, and their file(s) are available for download');
insert into order_item_status (id, name, description) values (4, 'Refunded', 'seller has used the Refund action');

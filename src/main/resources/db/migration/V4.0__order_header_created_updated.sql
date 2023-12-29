alter table order_header
    add column created_date TIMESTAMP;
    
 alter table order_header
    add column last_modified_date TIMESTAMP DEFAULT NOW();
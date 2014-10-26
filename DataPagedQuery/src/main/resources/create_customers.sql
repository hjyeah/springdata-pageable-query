use datapagedquery;
delimiter //

drop procedure if exists create_customers//
create procedure create_customers() 
begin
	declare v_id int;
	declare i int;
	
	set autocommit = 0;
	set i = 0;
	
	while	i <= 100 do
	
		select sequence_next_hi_value into v_id from hibernate_sequences where  sequence_name = 'Customer';
	
		update hibernate_sequences
		set sequence_next_hi_value = v_id + 1
		where  sequence_name = 'Customer';
		
		insert into customer(id, name) values(v_id, concat('customer_',v_id));
					
        set i = i + 1; 
    end while;
	
	commit;
	
end
//

call create_customers() 
//

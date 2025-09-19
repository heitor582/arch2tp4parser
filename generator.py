import random

mnemonic_map = {
    "umL":   '0', "zeroL": '1', "AonB":  '2', "nAonB": '3',
    "AeBn":  '4', "nB":    '5', "nA":    '6', "nAxnB": '7',
    "AxB":   '8', "copiaA":'9', "copiaB":'A', "AeB":   'B',
    "AenB":  'C', "nAeB":  'D', "AoB":   'E', "nAeBn": 'F'
}

mnemonics_list = list(mnemonic_map.keys())

assignment_counts = {
    'X': 2453,
    'Y': 2452,
    'W': 95
}

INPUT_FILENAME = "entrada_gerada.ula"
OUTPUT_FILENAME = "saida_gerada.hex"

total_lines = sum(assignment_counts.values())
w_assignments = assignment_counts.get('W', 0)

print(f"Configuração do Gerador:")
print(f" - Total de linhas a serem geradas: {total_lines}")
print(f" - Atribuições a 'X': {assignment_counts.get('X', 0)}")
print(f" - Atribuições a 'Y': {assignment_counts.get('Y', 0)}")
print(f" - Atribuições a 'W': {w_assignments}")
print("-" * 30)

hex_values = "0123456789ABCDEF"

operation_plan = []
for op_type, count in assignment_counts.items():
    operation_plan.extend([op_type] * count)

random.shuffle(operation_plan)

current_X = '0'
current_Y = '0'

try:
    with open(INPUT_FILENAME, 'w') as f_input, open(OUTPUT_FILENAME, 'w') as f_output:
        f_input.write(f"inicio:\n")
        for operation in operation_plan:
            if operation == 'W':
                chosen_mnemonic = random.choice(mnemonics_list)
                f_input.write(f"W={chosen_mnemonic};\n")
                
                w_value = mnemonic_map[chosen_mnemonic]
                output_line = f"{current_X}{current_Y}{w_value}\n"
                f_output.write(output_line)
                
            elif operation == 'X':
                new_value = random.choice(hex_values)
                current_X = new_value
                f_input.write(f"X={current_X};\n")
                
            elif operation == 'Y':
                new_value = random.choice(hex_values)
                current_Y = new_value
                f_input.write(f"Y={current_Y};\n")
        f_input.write(f"fim.\n")

    print(f"Arquivos gerados com sucesso!")
    print(f" - Arquivo de entrada: '{INPUT_FILENAME}' ({total_lines} linhas)")
    print(f" - Arquivo de saída: '{OUTPUT_FILENAME}' ({w_assignments} linhas)")

except Exception as e:
    print(f"Ocorreu um erro: {e}")
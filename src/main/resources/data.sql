INSERT INTO specialties (name, description, consultation_duration_minutes, created_at, updated_at)
VALUES
    ('Clínico Geral', 'Atendimento clínico geral e encaminhamentos.', 30, now(), now()),
    ('Cardiologia', 'Especialista em doenças do coração.', 30, now(), now()),
    ('Pediatria', 'Atendimento médico de crianças e adolescentes.', 30, now(), now()),
    ('Dermatologia', 'Diagnóstico e tratamento de doenças da pele.', 30, now(), now()),
    ('Ginecologia', 'Saúde do sistema reprodutor feminino.', 30, now(), now()),
    ('Ortopedia', 'Tratamento do sistema músculo-esquelético.', 30, now(), now()),
    ('Neurologia', 'Trata distúrbios do sistema nervoso.', 30, now(), now()),
    ('Endocrinologia', 'Trata distúrbios hormonais e metabólicos.', 30, now(), now())
ON CONFLICT (name) DO NOTHING;
